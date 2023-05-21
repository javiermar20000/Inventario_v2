package com.example.inventario_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import java.util.PropertyResourceBundle;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference contacto = (Preference)findPreference("informacion_contacto");
        Preference importuwu = (Preference)findPreference("import_preference");
        Preference exportuwu = (Preference)findPreference("export_preference");
        Preference system = (Preference)findPreference("system");
        Preference uwu = (Preference) findPreference("uwu");
        Preference theme = (Preference) findPreference("theme_preference");
        Preference grilla = (Preference) findPreference("Grilla");


        contacto.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Contacto: +569 7871 3797\ne-mail: javiermar2000@gmail.com",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        importuwu.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Base de Datos importada con éxito",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        exportuwu.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Base de Datos exportada con éxito",Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        system.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Compilado con Android 10 (x86) API 29 para Pixel 4",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        uwu.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.sound_long);
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (mp.isPlaying() || mp.isLooping()) {
                    Toast.makeText(getContext(),"canción detenida",Toast.LENGTH_SHORT).show();
                    mp.stop();
                } else {
                    Toast.makeText(getContext(),"canción iniciada",Toast.LENGTH_SHORT).show();
                    mp.start();
                }
                return true;
            }
        });

        theme.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Modo Oscuro desactivado",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        grilla.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getContext(),"Grilla Desactivada",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
