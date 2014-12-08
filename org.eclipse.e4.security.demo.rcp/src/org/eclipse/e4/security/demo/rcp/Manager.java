/**
 *
 */
package org.eclipse.e4.security.demo.rcp;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;

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
	}
}
