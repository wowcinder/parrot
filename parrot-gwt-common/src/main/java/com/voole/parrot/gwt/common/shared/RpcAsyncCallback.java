package com.voole.parrot.gwt.common.shared;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.info.Info;
import com.voole.parrot.shared.exception.NoLoginException;
import com.voole.parrot.shared.exception.PermissionException;
import com.voole.parrot.shared.exception.SharedException;

public abstract class RpcAsyncCallback<T> implements AsyncCallback<T> {
	public static <T> RpcAsyncCallback<T> dealWith(final GwtCallBack<T> callback) {
		return new RpcAsyncCallback<T>() {

			@Override
			protected void _onSuccess(T result) {
				callback.setResult(result);
				callback.succeed();
			}

			@Override
			public void _onFailure(Throwable caught) {
				super._onFailure(caught);
				callback.fail();
			}
		};
	}

	public void post() {

	}

	protected abstract void _onSuccess(T result);

	public void onSuccess(T result) {
		_onSuccess(result);
		post();
	}

	public void onFailure(Throwable caught) {
		_onFailure(caught);
		post();
	}

	protected void _onFailure(Throwable caught) {
		if (caught instanceof PermissionException) {
			AlertMessageBox d = new AlertMessageBox("操作失败", "权限不足");
			d.show();
		} else if (caught instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) caught)
					.getConstraintViolations();
			String sb = "";
			for (ConstraintViolation<?> cv : sets) {
				if (!"".equals(sb)) {
					sb += "<br />";
				}
				sb += cv.getPropertyPath() + " " + cv.getMessage();
			}
			Info.display("操作失败", sb);
		} else if (caught instanceof NoLoginException) {
			Info.display("操作失败", "登录超时");
		} else if (caught instanceof SharedException) {
			Info.display("操作失败", caught.getMessage());
		} else {
			Info.display("操作失败", "未知异常!!");
		}
	}

}
