package com.fxsession.fastplus.receiver.moex;



import org.openfast.Message;
import org.openfast.SequenceValue;

import com.fxsession.fastplus.fpf.FPFMessage;
import com.fxsession.fastplus.fpf.FPFeedDispatcher;

/**
 * @author Dmitry Vulf
 * 
 * Orders snapshoot
 *
 */
public class MoexFeedOLS extends MoexFeed {

	/**
	 * @param dispatcher
	 */
	public MoexFeedOLS(FPFeedDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public String getTemplateID() {
		return "3300";
	}

	@Override
	public String getSiteID() {
		return "OLS-A";
	}
	
	@Override
	public void processMessage(Message message) {
		if (message.getTemplate().getId().equals(getTemplateID())){
			FPFMessage fmessage = new FPFMessage(SYMBOL); 	
			String value = message.getString(FPFMessage.getFieldName(MSGSEQNUM));
			fmessage.putFieldValue(MSGSEQNUM, value);
			value = message.getString(FPFMessage.getFieldName(SYMBOL));
			fmessage.putFieldValue(SYMBOL, value);
            value = message.getString(FPFMessage.getFieldName(RPTSEQ));
	        fmessage.putFieldValue(RPTSEQ, value);
			SequenceValue secval =message.getSequence (FPFMessage.getFieldName(GROUPMDENTRIES));
			for (int i=0;i < secval.getValues().length;i++){
				value =  secval.getValues()[i].getString(FPFMessage.getFieldName(MDENTRYID));
				fmessage.putFieldValue(MDENTRYID, value);
		        value = secval.getValues()[i].getString(FPFMessage.getFieldName(MDENTRYTYPE));
		        fmessage.putFieldValue(MDENTRYTYPE, value);
		        value = secval.getValues()[i].getString(FPFMessage.getFieldName(MDENTRYSIZE));
		        fmessage.putFieldValue(MDENTRYSIZE, value);
		        value = secval.getValues()[i].getString(FPFMessage.getFieldName(MDENTRYPX));
		        fmessage.putFieldValue(MDENTRYPX, value);
				dispatcher.dispatch(this,fmessage);
			}
			
		}
		else 
		 processHeartbeat(message); 
	}


}
