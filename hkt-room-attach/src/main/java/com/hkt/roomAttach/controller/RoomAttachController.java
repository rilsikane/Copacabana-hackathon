package com.hkt.roomAttach.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hkt.roomAttach.dto.AttachDto;
import com.hkt.roomAttach.service.RoomAttachService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;



@RestController
public class RoomAttachController {
	
	@Autowired
	RoomAttachService roomAttachService;

	@ResponseBody
	@RequestMapping(value = "/searchRoomAttach.service", method = { RequestMethod.POST })
	public List<AttachDto> searchRoomAttach(@RequestHeader HttpHeaders headers, @RequestBody AttachDto criteria){
		return roomAttachService.searchRoomAttach(criteria);
	}
	@ResponseBody
	@RequestMapping(value = "/uploadFile.service", method = { RequestMethod.POST })
	public String uploadfile(@RequestHeader HttpHeaders headers,@RequestParam List<MultipartFile> files, @RequestParam Map<String, String> params){
		 JSch jsch = new JSch();
	        Session session = null;
	        String userNo = null;
	        String reserveId = null;
	        String ext = null;
	        try {
	        	reserveId = params.get("reserveId");
	        	userNo = params.get("userNo");
	        	ext = files.get(0).getOriginalFilename().split("\\.")[1];
	        	
	            session = jsch.getSession("root", "128.199.125.246", 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword("oms123456");
	            session.connect();
	            
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel;
	            sftpChannel.mkdir("/home/slipAttach/");

	            sftpChannel.put(files.get(0).getInputStream(), "/home/slipAttach/"+reserveId+"-"+userNo+"."+ext);
	            sftpChannel.exit();
	            session.disconnect();
	        } catch (JSchException e) {
	            e.printStackTrace();  
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return "Upload Complete";
	}
}
