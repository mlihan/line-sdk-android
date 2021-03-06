package com.linecorp.linesdktest.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public final class TestSettingManager {
    private static final String DEFAULT_CHANNEL_ID = "1620019587";
    private static final TestSetting DEFAULT_SETTING = new TestSetting(DEFAULT_CHANNEL_ID);

    private static final String SHARED_PREFERENCE_KEY = "test_settings";
    private static final String DATA_KEY_PREFIX_CHANNEL_ID = "channel_id_";

    private TestSettingManager() {
        // To prevent instantiation
    }

    @NonNull
    public static TestSetting getSetting(@NonNull Context context, int id) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String channelId = sharedPreferences.getString(DATA_KEY_PREFIX_CHANNEL_ID + id, "");
        if (TextUtils.isEmpty(channelId)) {
            return DEFAULT_SETTING;
        }

        return new TestSetting(channelId);
    }

    public static void save(@NonNull Context context, int id, @NonNull TestSetting setting) {
        context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
               .edit()
               .putString(DATA_KEY_PREFIX_CHANNEL_ID + id, setting.getChannelId())
               .apply();
    }
}
