package com.org.utils.analysis;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLUseStatement;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class BloodAnalysis {
    public Map<String, TreeSet<String>> bloodAnalysis(String sql) {

        Map<String, TreeSet<String>> result = new HashMap<String, TreeSet<String>>();
        List<SQLStatement> stmts = SQLUtils.parseStatements(sql, JdbcConstants.HIVE);
        TreeSet<String> selectSet = new TreeSet<String>();
        TreeSet<String> updateSet = new TreeSet<String>();
        TreeSet<String> insertSet = new TreeSet<String>();
        TreeSet<String> deleteSet = new TreeSet<String>();
        stmts.forEach(stmt -> {
            SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(stmts, JdbcConstants.HIVE);
            String database = "DEFAULT";
            if (stmt instanceof SQLUseStatement) {
                database = ((SQLUseStatement) stmt).getDatabase().getSimpleName();
            }
            stmt.accept(statVisitor);
            Map<TableStat.Name, TableStat> tables = statVisitor.getTables();
            if (tables != null) {
                final String db = database;
                for (Map.Entry<TableStat.Name, TableStat> table : tables.entrySet()) {
                    TableStat.Name tableName = table.getKey();
                    TableStat stat = table.getValue();

                    if (stat.getCreateCount() > 0 || stat.getInsertCount() > 0) { //create
                        String insert = tableName.getName();
                        if (!insert.contains("."))
                            insert = db + "." + insert;
                        insertSet.add(insert);
                    } else if (stat.getSelectCount() > 0) { //select
                        String select = tableName.getName();
                        if (!select.contains("."))
                            select = db + "." + select;
                        selectSet.add(select);
                    } else if (stat.getUpdateCount() > 0) { //update
                        String update = tableName.getName();
                        if (!update.contains("."))
                            update = db + "." + update;
                        updateSet.add(update);
                    } else if (stat.getDeleteCount() > 0) { //delete
                        String delete = tableName.getName();
                        if (!delete.contains("."))
                            delete = db + "." + delete;
                        deleteSet.add(delete);
                    }
                }
            }
        });
        result.put("select", selectSet);
        result.put("insert", insertSet);
        result.put("update", updateSet);
        result.put("delete", deleteSet);

        return result;
    }

    public static void main(String[] args) {
        String sql =
                "insert overwrite table student partition(stat_date=\"20120802\") " +
                        "select id,age,name from student_tmp where stat_date=\"20120801\" sort by age;";

        System.out.println(JSON.toJSONString(new BloodAnalysis().bloodAnalysis(sql)));

    }
}