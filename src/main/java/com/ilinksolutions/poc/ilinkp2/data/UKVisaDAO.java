package com.ilinksolutions.poc.ilinkp2.data;

import java.util.List;
import com.ilinksolutions.poc.ilinkp2.domains.UKVisaMessage;

/**
 *
 */
public interface UKVisaDAO
{
    void save(UKVisaMessage entry);
    List<UKVisaMessage> list();
}
