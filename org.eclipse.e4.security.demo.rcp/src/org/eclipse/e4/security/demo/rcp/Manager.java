/**
 *
 */
package org.eclipse.e4.security.demo.rcp;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * @author SHomeier
 */
public class Manager
{
	@PostContextCreate
	void postContextCreate(IEclipseContext context)
	{
		System.out.println("Called LCM");
		System.out.println("IEclipseContext: " + context.toString());

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
			System.out.println("2a");
			MPart part = partService.findPart("org.eclipse.e4.security.demo.login.part.LoginPart");

			if (part == null)
				System.out.println("PART IS NULL");
			else
				System.out.println("Part is NOOT null");
			System.out.println("PartService is NOOOT NULL");
		}

	}
}
