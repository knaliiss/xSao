package dev.knalis.xsao.utils.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import dev.knalis.xsao.interfaces.IAction;
import dev.knalis.xsao.model.actions.*;

public class GsonConfig {
    public static Gson createGson() {
        JsonSerializer<IAction> serializer = (src, typeOfSrc, context) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", src.getClass().getSimpleName());
            jsonObject.add("data", context.serialize(src));
            return jsonObject;
        };

        JsonDeserializer<IAction> deserializer = (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            JsonElement data = jsonObject.get("data");

            switch (type) {
                case "KeyDownAction":
                    return context.deserialize(data, KeyDownAction.class);
                case "KeyUpAction":
                    return context.deserialize(data, KeyUpAction.class);
                case "MouseDownAction":
                    return context.deserialize(data, MouseDownAction.class);
                case "MouseUpAction":
                    return context.deserialize(data, MouseUpAction.class);
                case "SleepAction":
                    return context.deserialize(data, SleepAction.class);
                default:
                    throw new JsonParseException("Unknown type: " + type);
            }
        };

        return new GsonBuilder()
                .registerTypeAdapter(IAction.class, serializer)
                .registerTypeAdapter(IAction.class, deserializer)
                .setPrettyPrinting()
                .create();
    }
}