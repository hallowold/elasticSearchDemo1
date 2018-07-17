package com.example.demo.common.util.requestcreater;

import com.example.demo.request.group.SysGroupCreateRequest;
import com.example.demo.request.group.SysGroupUpdateRequest;
import com.example.demo.security.entity.SysGroup;

import java.util.*;

/**
 * @author  liuqitian
 * @date : 2018/7/16 15:33
 * @version  V1.2
 * 本类用于将group相关的request对象转化实体
*/
public class GroupRequestUtil {

    /**
     * 将GroupCreateRequest转化为Map<String, Object>
     * 		"entity"对应角色实体，"roleIds"对应权限id数组
     * @param 	request
     * @return	Map<String, Object>
     */
    public static Map<String, Object> createSysGroupByCreateRequest(SysGroupCreateRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysGroup info = new SysGroup();
        if(request == null) {
            return null;
        } else {
            if(request.getName() != null) {
                info.setName(request.getName());
            }
            if(request.getPid() != null) {
                info.setpId(request.getPid());
            }
            resultMap.put("entity", info);
            resultMap.put("roleIds", request.getRoleIds());

        }
        return resultMap;
    }

    /**
     * 将SysGroupCreateRequest集合转化为SysGroup集合
     * @param 	requests
     * @return	List<Map<String, Object>>
     */
    public static List<Map<String, Object>> createSysGroupByCreateRequests(List<SysGroupCreateRequest> requests) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(SysGroupCreateRequest request : requests) {
            if(createSysGroupByCreateRequest(request) != null) {
                resultList.add(createSysGroupByCreateRequest(request));
            }
        }
        return resultList;
    }

    /**
     * 将GroupUpdateRequest转化为Map<String, Object>
     * 		"entity"对应角色实体，"roleIds"对应权限id数组
     * @param 	request
     * @return	Map<String, Object>
     */
    public static Map<String, Object> createSysGroupByUpdateRequest(SysGroupUpdateRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysGroup info = new SysGroup();
        if(request == null) {
            return null;
        } else {
            if(request.getId() != null) {
                info.setId(request.getId());
            }
            if(request.getName() != null) {
                info.setName(request.getName());
            }
            if(request.getPid() != null) {
                info.setpId(request.getPid());
            }
            resultMap.put("entity", info);
            resultMap.put("roleIds", request.getRoleIds());

        }
        return resultMap;
    }

    /**
     * 将SysGroupCreateRequest集合转化为SysGroup集合
     * @param 	requests
     * @return	List<Map<String, Object>>
     */
    public static List<Map<String, Object>> createSysGroupByUpdateRequests(List<SysGroupUpdateRequest> requests) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(SysGroupUpdateRequest request : requests) {
            if(createSysGroupByUpdateRequest(request) != null) {
                resultList.add(createSysGroupByUpdateRequest(request));
            }
        }
        return resultList;
    }
}

