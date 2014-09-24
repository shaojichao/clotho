package com.runmit.clotho.management.controller;

//import com.runmit.uc.core.domain.DeviceInfo;
//import com.runmit.uc.core.service.DeviceInfoService;
//import com.runmit.uc.management.common.ResponseVo;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;

/**
 * @author TianLiang
 */
//@Controller
//@RequestMapping(value = "/device")
public class DeviceManageController {
//
//    @Autowired
//    private DeviceInfoService deviceInfoService;
//
//    @RequestMapping(value = "/devicemanage")
//    public String getDeviceManage(HttpServletRequest request, Model model) {
//        List<DeviceInfo> uis = deviceInfoService.getAllDeviceInfo();
//        model.addAttribute("rv", new ResponseVo<DeviceInfo>(uis));
//        return "device/devicemanage/index";
//    }
//
//    @RequestMapping(value = "/adddeviceinfo")
//    public @ResponseBody boolean addDevice(@RequestParam(value = "devicesn", required = true) String devicesn,
//                          @RequestParam(value = "devicetype", required = true) int devicetype,
//                          @RequestParam(value = "location", required = true) String location,
//                          @RequestParam(value = "produceddate", required = false) String produceddate,
//                          HttpServletRequest request, Model model) {
//        DeviceInfo di;
//        try {
//            di = getDeviceInfo(devicesn, devicetype, location, produceddate);
//            deviceInfoService.addDeviceInfo(di);
//        }catch (Exception e){
////            TODO:加LOG
//            return false;
//        }
//        return true;
//    }
//
//    @RequestMapping(value = "/querydeviceinfo", method = RequestMethod.GET)
//         public @ResponseBody List<DeviceInfo> queryDevice(@RequestParam(value = "devicesn", defaultValue = "") String devicesn,
//                                                           @RequestParam(value = "devicetype", defaultValue= "-1") Integer devicetype,
//                                                           @RequestParam(value = "location", defaultValue = "") String location,
//                                                           @RequestParam(value = "produceddate", defaultValue = "") String produceddate,
//                                                           HttpServletRequest request, Model model) {
//        List<DeviceInfo> ldi=new ArrayList<>();
//        try {
//            DeviceInfo di=new DeviceInfo();
//            di.setDevicesn(devicesn);
//            ldi.add(deviceInfoService.getbysn(devicesn));
////                    deviceInfoService.queryDevice(devicesn, devicetype, location, produceddate);
//        }catch (Exception e){
////            TODO:加LOG
//            return null;
//        }
//        return ldi;
//    }
//
//    @RequestMapping(value = "/getdeviceinfobyid", method = RequestMethod.GET)
//    public @ResponseBody DeviceInfo getDevicebyid(@RequestParam(value = "deviceid") Integer deviceid,
//                                                      HttpServletRequest request, Model model) {
//        return deviceInfoService.getbyid(deviceid);
//    }
//
//    @RequestMapping(value = "/deletedeviceinfo", method = RequestMethod.POST)
//    public @ResponseBody boolean delDevicebyid(@RequestParam(value = "deviceid") int deviceid,
//                                                  HttpServletRequest request, Model model) {
//        try {
//            deviceInfoService.deleteDeviceInfo(deviceid);
//            return true;
//        }catch (Exception e){
////            TODO:加LOG
//            return false;
//        }
//    }
//
//    @RequestMapping(value = "/modifydeviceinfo", method = RequestMethod.GET)
//    public @ResponseBody List<DeviceInfo> modifyDevice(@RequestParam(value = "deviceid") Integer deviceid,
//                                                 @RequestParam(value = "devicesn") String devicesn,
//                                                 @RequestParam(value = "devicetype") Integer devicetype,
//                                                 @RequestParam(value = "location") String location,
//                                                 @RequestParam(value = "produceddate", defaultValue = "") String produceddate,
//                                                  HttpServletRequest request, Model model) {
//        DeviceInfo di=deviceInfoService.getbyid(deviceid);
//        di.setDevicesn(devicesn);
//        di.setDevicetype(devicetype);
//        di.setLocation(location);
//        if(StringUtils.isNotBlank(produceddate))di.setProduceddate(produceddate);
//        deviceInfoService.updateDeviceInfo(di);
//        List<DeviceInfo> ldi=new ArrayList<>();
//        ldi.add(deviceInfoService.getbyid(di.getDeviceid()));
//        return ldi;
//    }
//
//    @RequestMapping(value = "/getdeviceinfo", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    List<DeviceInfo> listdevice(
//            HttpServletRequest request, Model model) throws Exception {
//        List<DeviceInfo> deviceinfos = deviceInfoService.getAllDeviceInfo();
//        if(deviceinfos.size()>2000) return deviceinfos.subList(0,2000);
//        else return deviceinfos;
//    }
//
//    public DeviceInfo getDeviceInfo(String devicesn,int devicetype,String location,String producedate){
//        DeviceInfo di=new DeviceInfo();
//        di.setDevicesn(devicesn);
//        di.setDevicetype(devicetype);
//        di.setLocation(location);
//        di.setProduceddate(producedate);
//        di.setUpdateby("manageby");
//        di.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
//        di.setCreateby("manageby");
//        return di;
//    }

}
