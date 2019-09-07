package com.exs.util;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.SugarRecord;
import com.orm.util.NamingHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.orm.SugarContext.getSugarContext;

/**
 * Internal utility method for SugarORM
 */
public class SugarRecordExt extends SugarRecord {

    /**
     * Get SugarORM protected database object via reflection
     *
     * @return
     */
    public static SQLiteDatabase getDb() {
        SQLiteDatabase sqLiteDatabase = null;
        SugarContext context = getSugarContext();
        try {
            Method m = context.getClass().getDeclaredMethod("getSugarDb");
            m.setAccessible(true);
            SugarDb db = (SugarDb) m.invoke(context);
            sqLiteDatabase = db.getDB();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return sqLiteDatabase;
    }

    public static void markOutdated(Class className) {
        SugarRecord.executeQuery("UPDATE " + NamingHelper.toSQLName(className) + " SET UPDATED = ?", new String[]{"0"});
    }

    public static void clearOutdated(Class className) {
        SugarRecord.executeQuery("DELETE FROM " + NamingHelper.toSQLName(className) + " WHERE UPDATED = ?", new String[]{"0"});
    }

    /**
     * Perform save in background task and pass the subsequent process to onFinish().
     *
     * @param data
     * @param onFinish
     */
    public static void save(Object data, final Runnable onFinish) {

        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {
                SugarRecord.save(objects[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                onFinish.run();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data);

        SugarRecord.save(data);
    }

    public static String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

    public static String likeArrayInput(String[] string) {
        if (string.length < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(string[0]);
            for (int i = 1; i < string.length; i++) {
                sb.append(",");
                sb.append(string[i]);
            }
            return sb.toString();
        }
    }

    public static String[] makeArray(List<Long> data) {
        String[] result = new String[data.size()];
        int i = 0;
        for(Long l: data){
            result[i++] = l.toString();
        }
        return result;
    }
}
