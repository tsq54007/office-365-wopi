package com.example.office365wopi.rest;

import com.example.office365wopi.response.CheckFileInfoResponse;
import com.example.office365wopi.service.WopiProtocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/wopi")
public class WopiProtocalController {

    private WopiProtocalService wopiProtocalService;

    @Autowired
    public WopiProtocalController(WopiProtocalService wopiProtocalService) {
        this.wopiProtocalService = wopiProtocalService;
    }

    @GetMapping("/files/{name}/contents")
    public ResponseEntity<Resource> getFile(@PathVariable(name = "name") String name, HttpServletRequest request) throws UnsupportedEncodingException, FileNotFoundException {
        return wopiProtocalService.handleGetFileRequest(name, request);
    }

    @PostMapping("/files/{name}/contents")
    public void putFile(@PathVariable(name = "name") String name, @RequestBody byte[] content, HttpServletRequest request) throws IOException {
        wopiProtocalService.handlePutFileRequest(name, content, request);
    }


    @GetMapping("/files/{name}")
    public ResponseEntity<CheckFileInfoResponse> getFileInfo(@PathVariable(name = "name") String name, HttpServletRequest request) throws UnsupportedEncodingException, FileNotFoundException {
        return wopiProtocalService.handleCheckFileInfoRequest(name, request);
    }

    @PostMapping("/files/{name}")
    public ResponseEntity editFile(@PathVariable(name = "name") String name, HttpServletRequest request) {
        return wopiProtocalService.handleEditFileRequest(name, request);
    }

}
