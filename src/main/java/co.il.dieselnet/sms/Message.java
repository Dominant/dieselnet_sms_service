package co.il.dieselnet.sms;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;

public class Message {
    private String code;
    private String phone;

    public Message(String code, String phone) {
        this.code = code;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "{Code: " + code + ", Phone: " + phone + "}";
    }

    public static class JsonSerializer {

        public static Message deserialize(String jsonString)
                throws UnsupportedEncodingException {

            String body = new String(jsonString.getBytes(), "UTF-8");
            JSONObject jsonObject = new JSONObject(new JSONTokener(body));

            return new Message(jsonObject.getString("code"), jsonObject.getString("phone"));
        }
    }
}
