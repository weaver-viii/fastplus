package com.fxsession.fastplus.handler.moex;



import org.apache.log4j.Logger;

import com.fxsession.fastplus.fpf.FPFMessage;
import com.fxsession.fastplus.fpf.IFPFHandler;
import com.fxsession.fastplus.fpf.IFPFOrderBook;
import com.fxsession.fastplus.fpf.IFPField;

import com.fxsession.fastplus.fpf.OnCommand;

/**
 * @author Dmitry Vulf
 *
 */
public class MoexHandlerOLS implements IFPFHandler, IFPField {
	private static Logger mylogger = Logger.getLogger("OLS");

	@Override
	public String getInstrumentID() {
		return "EURUSD000TOM";	
	}

	@Override
	public OnCommand push(FPFMessage message) {
		OnCommand retval =  OnCommand.ON_PROCESS;
		
	    String rptseq = message.getFieldValue(RPTSEQ);
		String key =  message.getFieldValue(MDENTRYID);
	    String type = message.getFieldValue(MDENTRYTYPE);
	    String size = message.getFieldValue(MDENTRYSIZE);
	    String px = message.getFieldValue(MDENTRYPX);
	    mylogger.info(getInstrumentID()+
						"<" + FPFMessage.getFieldName(MDENTRYID)+">"+ key +
						"<" + FPFMessage.getFieldName(MDENTRYTYPE)+">"+ type + 
						"<" + FPFMessage.getFieldName(MDENTRYSIZE)+">"+ size + 
						"<" + FPFMessage.getFieldName(MDENTRYPX)+">"+ px + 
						"<" + FPFMessage.getFieldName(RPTSEQ) + ">" +rptseq);
	    return retval;
	}


}
