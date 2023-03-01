package com.zhi.mymall.config;

import com.alipay.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayClientConfig {
    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {

        AlipayConfig alipayConfig = new AlipayConfig();

        //设置网关地址
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        //设置应用Id
        alipayConfig.setAppId("2021003127629610");
        //设置应用私钥
        alipayConfig.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAKNtCfbvmmNLZaUvxTmSjO3PAVjB/fuLek86zlG6reybYKqYw9yP3V+hzf5pVsvJN5IZxxEkcsJ3r1CH1/QyTxYu66wDxYlTsms+CQ123OFG4NnFFwJ5Q5HhuY+C6o5yXo7BkWOHja6zNtZqR5L4uEtXtnGmJq19BCHloExv9wSbcK58Nj1iNgakocxATI4HlrXXGuh4gtHLiFtNzXu/0iGa9GtYWBtXQE8YbvyjGKVCvcDfu29CB38qbmDsdVFffpZkvyLvniTGXVvd9pzQEWRZQgLHFC2IewJGcc1BJDh+1V7oxBTzcSmXwyrPmSDC6vmhS8kL4C0XkpWtn1titAgMBAAECggEANxXYkG7MueQMFMjOjIyecw7VxOEG/XYDe5naaZOuMeTAIH/MaRWBDwpo7mP1aUp+HX7RTssIhd1gM4I9iqbWphlt4syp3hbe28f600mzWVJKxbwY2dtd8PUS0pf5ex9h6kfARJFznjWk09Jv+q9wEwujifsF3decHK7QyCwWhu3GwJa4sIb+wiAAVzrfLWCecGa6XASQ6C6NW6Z8NomNQ7OAOSoMpi88r73zwKtUbZFCg2C3yPh8wjI78GYOV50GqA0E8mFVqVGZ0cPePKqRU3zMFJ9Wl7IjWDPW09+UiAZj3E9wuaYB7G97dgeLa1Bprfu0WzhnN5sCWbsVVqxpnQKBgQDL8gS/IzelcPgLHFNpKqCh8VrwMsFgv88DfNO9sHPbD4esQbdXZVW9DI/aEQa0ZJs7HBMyXISkbsUTu3Wfl98Q9R7+47ZfXIwu3/5a/7MUNZOQstnUB/kMAVH7A3+wbCLovhsXXwGIQP1ApcSd8OLWdH7kqBE0CiG8rwh5DI4umwKBgQCg3uyCTC/YndgIlXylVXEgq31UFkkDyRqtRiDCx1P+zvLJwxAyKHIJRyiJo/GLaTgTHbeQZx/kbXwQV1eztR8Q1SwDdbzXzoKD" +
                "G7LfkyYLczfHvtQMvc0V3meuXX6Fl9dzB/cTe8o3+pOIgI1UUl4jBlse34Tf7RVLjLe4Jm4mVwKBgDj52xOe/hvNLK7F8hOOYiP77qDuPRIPZAEgbXMwCZ3of8nC7jqPR92x1bphSVM6TBe+ZyqJqFDb1VkG5Px6DEqLgGZLLZcEnvE1iLTZfKknspsZLDvNImmVMNcEija6byJAggcMdrwfE+FsrjpFosmw8AZnYa79KaGx8JcIx+wDAoGAE5BIrufZAbzgYtrbF7g2U8THLwyuwHkad2wJ2+Ylvt0oBYVlWNQ2Wg5/TY3UeCHJofmBM1x3Mm5mIg6dKcujBYcHHTyaRJxmns8lh677d3UBmZcJbqs+X6R2c2wHUPPQTbrwPzxUsU4V1wnbJiAGNrg1KKICJMFOT6vDQ+/YgW0CgYEAk37VR9C2yCPd87p0BergO4lpEv+tPr667YL4b0IGoofGq96cS4FSyqTyyVaLSwvDOPI71XVwyjRKYTEW478DMGKxMVoodapmNmQ5sTp/NMlqdvUf2CZAEKLuGlWvlKXalxOxbAVEdg6N8/Df5p/hMO3AcE2D0pClscrOr0G8by8=");
        //设置请求格式，固定值json
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        //设置字符集
        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
        //设置支付宝公钥
        alipayConfig.setAlipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq748FfeHxVwExqW55TUvf8wlxi4I0sCvAAAN9x4nzO7pfSgzRFokCpc/LWBguzK04ny6AhQX7bbSkC2kmwdbcJgd5852MO5kd8Cdse+F9OnzJRbDpEi0aG3Xmco+s94VbHhUTvVrq0I4PCXEM6HWFZuz54pTZNS3mJ0v17SoF08HKLXlmmWXAsmcQnVHqmMO6PgPivRU5MMaLcNJbjOCCZfFPXoNEpmWxBxv5SRlK/zYx72fN/f0o9meUvEbhij6mi+alM16i97NyF0mLUGTSTutHqFpa33Uqo0zm3pqEWxYeUL8N4iUulio6e3ydZTH2+faZT6GusKb+yOqQbvAnQIDAQAB");
        //设置签名类型
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        //构造client
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

        return alipayClient;
    }
}
