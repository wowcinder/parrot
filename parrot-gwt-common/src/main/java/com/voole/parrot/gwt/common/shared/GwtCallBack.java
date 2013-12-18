package com.voole.parrot.gwt.common.shared;

public abstract class GwtCallBack<T> {

	public static abstract class GwtCallBackChain<M, N> extends GwtCallBack<M> {
		private final GwtCallBack<N> next;

		public GwtCallBackChain(GwtCallBack<N> next) {
			this.next = next;
		}

		public void succeed() {
			super.succeed();
			next.setResult(getNextResult());
			next.succeed();
		}

		protected abstract N getNextResult();

		public void cancel() {
			super.cancel();
			next.cancel();
		}

		public void fail() {
			super.fail();
			next.fail();
		}
	}

	private T result;

	public GwtCallBack() {
	}

	protected void pre() {

	}

	protected void post() {

	}

	public void succeed() {
		pre();
		_succeed();
		post();
	}

	protected abstract void _succeed();

	public void fail() {
		pre();
		_fail();
		post();
	}

	protected void _fail() {

	}

	public void cancel() {
		pre();
		_cancel();
		post();
	}

	protected void _cancel() {

	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
