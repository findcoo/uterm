package com.uterm.toolbox;

import static com.uterm.toolbox.HashFunction.digestWithSHA256;
import static org.assertj.core.api.Assertions.assertThat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.Test;


/**
 * HashFunctionTests
 */
public class HashFunctionTests {

    @Test
    public void sha256() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(new String("aaaaa").getBytes());

        String comparison = Base64.getEncoder().encodeToString(md.digest());
        String target = digestWithSHA256(new String[]{"aaaaa"});
        
        assertThat(target).isEqualTo(comparison);
    }
    
}