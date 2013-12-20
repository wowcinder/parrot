package com.voole.parrot.gwt.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.voole.parrot.util.ClassScanner;

public class RpcServiceAsyncGenerator extends BaseJavaSourceGenerator {
	private static final String RPCSERVICE_PACKAGE = "com.voole.parrot.gwt.common.shared.rpcservice";
	private static final String RPCSERVICE_ASYNC_PACKAGE = RPCSERVICE_PACKAGE;

	private final JDefinedClass rpcServiceUtils;

	public RpcServiceAsyncGenerator() throws JClassAlreadyExistsException {
		rpcServiceUtils = getjCodeModel()._class(
				RPCSERVICE_ASYNC_PACKAGE + ".RpcServiceUtils");
	}

	public static void main(String[] args) throws Exception {
		RpcServiceAsyncGenerator t = new RpcServiceAsyncGenerator();
		t.work();
	}

	public void work() throws Exception {
		ClassScanner scaner = new ClassScanner(RPCSERVICE_PACKAGE);
		for (Class<?> clazz : scaner.getClazzes()) {
			if (RemoteService.class.isAssignableFrom(clazz)) {
				generateAsyncClass(clazz);
			}
		}
		build();
	}

	private void generateAsyncClass(Class<?> clazz)
			throws JClassAlreadyExistsException, IOException,
			ClassNotFoundException {
		TypeToken<?> typeToken = TypeToken.of(clazz);
		String name = clazz.getSimpleName() + "Async";
		String packagee = RPCSERVICE_ASYNC_PACKAGE;
		JDefinedClass dc = getjCodeModel()._class(packagee + "." + name,
				ClassType.INTERFACE);
		for (Method method : clazz.getMethods()) {
			generateAsyncMethod(typeToken, dc, method);
		}
		writeGwtInstance(clazz, dc);
	}

	private void writeGwtInstance(Class<?> clazz, JDefinedClass dc)
			throws JClassAlreadyExistsException {
		rpcServiceUtils.field(
				JMod.PUBLIC + JMod.STATIC + JMod.FINAL,
				dc,
				clazz.getSimpleName(),
				getjCodeModel().ref(GWT.class).staticInvoke("create")
						.arg(JExpr.dotclass(getjCodeModel().ref(clazz))));
	}

	public void generateAsyncMethod(TypeToken<?> typeToken, JDefinedClass dc,
			Method method) throws IOException, ClassNotFoundException {
		JMethod jMethod = dc.method(JMod.PUBLIC, void.class, method.getName());
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		for (int i = 0; i < genericParameterTypes.length; i++) {
			Type parameterType = genericParameterTypes[i];
			String parameterName = "arg" + i;
			jMethod.param(getJType(typeToken, parameterType), parameterName);
		}
		Type type = method.getGenericReturnType();
		JType returnClass = getJType(typeToken, type);
		jMethod.param(
				getjCodeModel().ref(AsyncCallback.class).narrow(returnClass),
				"callback");
	}
}
