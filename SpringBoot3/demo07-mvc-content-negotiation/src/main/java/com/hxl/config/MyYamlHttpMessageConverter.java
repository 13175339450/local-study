package com.hxl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 自定义配置 Yaml 格式的消息转换器
 */
public class MyYamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    // 把对象转换成yaml
    private ObjectMapper objectMapper = null;

    public MyYamlHttpMessageConverter() {
        // TODO: 告诉SpringBoot这个MessageConverter支持哪一种媒体类型！！！
        super(new MediaType("text", "yaml", Charset.forName("UTF-8")));
        YAMLFactory yamlFactory =
                new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(yamlFactory);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 判断是对象类型，而非基本数据类型
        return true;
    }

    @Override // 读取 @RequestBody 对应参数的
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override // 写 @ResponseBody 对应数据
    protected void writeInternal(Object methodReturnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // try-with 写法 自动关流
        try (OutputStream os = outputMessage.getBody()) {
            this.objectMapper.writeValue(os, methodReturnValue);
        }
    }
}
