/**
 *
 */
package org.eclipse.e4.security.demo.login;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.security.demo.ui.LoginPart;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.osgi.framework.FrameworkUtil;

/**
 * @author SHomeier
 */
public class DummyCallbackHandler implements CallbackHandler
{
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
	{
		System.out.println("CallbackHandler successfully triggered!!!");
		LoginPart.setUserText("Fram CallbackHandler");

		IEclipseContext context =
			EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(LoginPart.class).getBundleContext());
		System.out.println("1");
		if (context.containsKey(EPartService.class))
			System.out.println("Contains Part Service");
		if (context.containsKey(MApplication.class))
			System.out.println("Contains MApplication Service");
		if (context.containsKey(EModelService.class))
			System.out.println("Contains EModelService Service");
		EPartService partService = context.get(EPartService.class);

		if (partService == null)
		{
			System.out.println("PartService is null...");
		}
		else
		{
			System.out.println("PartService is NOOOT NULL");
		}

		System.out.println("2a");
		MPart part = partService.findPart("org.eclipse.e4.security.demo.login.part.LoginPart");

		if (part == null)
			System.out.println("PART IS NULL");
		else
			System.out.println("Part is NOOT null");

		System.out.println("3");
		LoginPart loginPart = (LoginPart) part.getObject();

		if (loginPart == null)
			System.out.println("loginPart IS NULL");
		else
			System.out.println("loginPart is NOOT null");

		// System.out.println("Calling LoginPart ....");
		// loginPart.setUserText("From Callback");

	}
}
