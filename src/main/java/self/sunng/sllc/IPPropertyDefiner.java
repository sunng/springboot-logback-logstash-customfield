package self.sunng.sllc;

import ch.qos.logback.core.PropertyDefinerBase;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by sunxiaodong on 2016/12/7.
 */
public class IPPropertyDefiner extends PropertyDefinerBase {

    private static final String ip = "\"" + getHostAddress() + "\"";

    @Override
    public String getPropertyValue() {
        return ip;
    }

    private static String getHostAddress() {
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            return "";
        }
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements())
        {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address)
                {
                    return ip.getHostAddress();
                }
            }
        }

        return "";
    }
}
