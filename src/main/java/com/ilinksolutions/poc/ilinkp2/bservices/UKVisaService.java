package com.ilinksolutions.poc.ilinkp2.bservices;

import com.ilinksolutions.poc.ilinkp2.data.UKVisaDAO;
import com.ilinksolutions.poc.ilinkp2.data.impl.UKVisaDAOImpl;
import com.ilinksolutions.poc.ilinkp2.domains.UKVisaMessage;

import java.util.List;

/**
 *
 */
public class UKVisaService
{
	private UKVisaDAO dao = new UKVisaDAOImpl();

	public void addEntry(UKVisaMessage entry)
	{
		dao.save(entry);
	}

	public List<UKVisaMessage> getAllEntries()
	{
		return dao.list();
	}
}
