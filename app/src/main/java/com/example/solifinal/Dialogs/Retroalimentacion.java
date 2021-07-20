package com.example.solifinal.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Retroalimentacion extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        String msg = args.getString("ResCorrecta");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Retroalimentación")
                .setMessage("Respuesta Correcta:\n"+msg)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        return builder.create();
    }
}
