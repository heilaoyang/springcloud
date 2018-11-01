package com.login.utlis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.login.demo.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JsonToObject {
    public static SecurityUser parsetoSecurityUser(String str) throws ParseException {
        JSONObject jsonObject = JSON.parseObject(str);
        String string = jsonObject.get("authorities").toString();
        String[] split;
        Set<GrantedAuthority> set=new HashSet<GrantedAuthority>();
        if (string.contains(",")){
            split = StringUtils.split(string,',');
            for (String strs:split){
                String roles = StringUtils.substring(strs, strs.lastIndexOf("ROLE"), strs.lastIndexOf("\""));
                set.add(new SimpleGrantedAuthority(roles));
            }
        }
        else{
            String roles = StringUtils.substring(string, string.lastIndexOf("ROLE"), string.lastIndexOf("\""));
            set.add(new SimpleGrantedAuthority(roles));
        }
        SecurityUser securityUser = new SecurityUser(jsonObject.getString("username"), jsonObject.getString("password"),set);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.parseLong(jsonObject.getString("lastPasswordResetDate")));
        securityUser.setLastPasswordResetDate(date);
        return securityUser;
    }
    public static void main(String args[]) throws ParseException {
        SecurityUser securityUser = parsetoSecurityUser("{\"accountNonExpired\":true,\"accountNonLocked\":true,\"authorities\":[{\"authority\":\"ROLE_ADMIN\"}],\"credentialsNonExpired\":true,\"enabled\":true,\"lastPasswordResetDate\":1540772540000,\"password\":\"$2a$10$u.iBOhtUuCJWtuyAQmg0helZJXtwLbImK4SBJrOvmVHydDwlQG5e2\",\"username\":\"13025271274\"}");
        System.out.println(securityUser.getLastPasswordResetDate());
    }
}
