package com.ilinksolutions.poc.ilinkp2.wservices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilinksolutions.poc.ilinkp2.bservices.UKVisaService;
import com.ilinksolutions.poc.ilinkp2.domains.UKVisaMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 */
public class MainServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UKVisaService bService = new UKVisaService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getServletContext().getResourceAsStream("/WEB-INF/index.html"), "UTF-8"));
		try
		{
			String line;
			boolean insideLoop = false;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				if (line.trim().equals("<!-- begin repeat for each entry -->"))
				{
					insideLoop = true;
				}
				else if (line.trim().equals("<!-- end repeat for each entry -->"))
				{
					insideLoop = false;
					String entryTemplate = sb.toString();
						for (UKVisaMessage entry: bService.getAllEntries())
						{
							out.println(entryTemplate.replace("{{ summary }}",
									escapeHtml(entry.getSummary())).replace("{{description}}",
									escapeHtml(entry.getDescription())));
						}
				}
				else if (insideLoop)
				{
					sb.append(line).append("\n");
				}
				else
				{
					out.println(line);
				}
			}
		}
		finally
		{
			reader.close();
		}
	}

	private String escapeHtml(String text)
	{
		return text.replace("<", "&lt;");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String summary = req.getParameter("summary");
		String description = req.getParameter("description");
		bService.addEntry(new UKVisaMessage(summary, description));
		resp.sendRedirect("dbMessages.html");
	}
}
