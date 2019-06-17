package com.zan.hu;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.HashMap;

import static java.util.Collections.singletonMap;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-17 18:48
 * @Description todo
 **/
@val
public class FeignMultipartSpringFormEncoder extends SpringFormEncoder {

    /**
     * Constructor with specified delegate encoder.
     *
     * @param delegate delegate encoder, if this encoder couldn't encode object.
     */
    public FeignMultipartSpringFormEncoder(Encoder delegate) {
        super(delegate);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType.equals(MultipartFile[].class)) {
            val files = (MultipartFile[]) object;
            //val data = new HashMap<String, Object>(files.length, 1.F);
            val data = singletonMap(files.length == 0 ? "" : files[0].getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            val file = (MultipartFile) object;
            val data = singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (isMultipartFileCollection(object)) {
            val iterable = (Iterable<?>) object;
            val data = new HashMap<String, Object>();
            for (val item : iterable) {
                val file = (MultipartFile) item;
                data.put(file.getName(), file);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else {
            super.encode(object, bodyType, template);
        }
    }

    private boolean isMultipartFileCollection(Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        }
        val iterable = (Iterable<?>) object;
        val iterator = iterable.iterator();
        return iterator.hasNext() && iterator.next() instanceof MultipartFile;
    }
}
