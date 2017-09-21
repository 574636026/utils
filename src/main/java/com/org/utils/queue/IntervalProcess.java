package com.org.utils.queue;

import com.org.utils.log.LogUtils;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class IntervalProcess<T> {

	/** 如果任务超过线程池大小,会在一个queue里等待执行 */
	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	private Future<?> future = null;

	private int seconds = 0;

	private Queue<T> queue;

	public IntervalProcess(Queue<T> queue) {
		this.queue = queue;
	}

	public IntervalProcess(Queue<T> queue, int seconds) {
		this.queue = queue;
		this.seconds = seconds;
	}

	public abstract void execute(T entity);

	public void start() {

		if (queue == null || queue.isEmpty()) {
			return;
		}

		// 每次执行时间为上一次任务结束起向后推一个时间间隔,下次任务执行时间为初始延迟+任务执行时间+延迟时间
		future = executor.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {

				if (queue.isEmpty()) {

					LogUtils.d("执行完成，任务关闭");

					if (future != null) {
						future.cancel(true);
						executor.shutdown();
					}

				}

				execute(queue.poll());

			}
		}, 0, seconds, TimeUnit.SECONDS);

	}

}
