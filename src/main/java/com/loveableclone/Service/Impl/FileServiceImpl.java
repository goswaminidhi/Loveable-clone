package com.loveableclone.Service.Impl;

import com.loveableclone.Service.FileService;
import com.loveableclone.dto.project.FileContentResponse;
import com.loveableclone.dto.project.FileNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
