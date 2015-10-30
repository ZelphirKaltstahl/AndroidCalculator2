package com.example.xiaolong.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends Activity {

    private EditText input_output_field;
    private ArrayList<String> text_history = new ArrayList<>();


//    private Button number_0_button;
//    private Button number_1_button;
//    private Button number_2_button;
//    private Button number_3_button;
//    private Button number_4_button;
//    private Button number_5_button;
//    private Button number_6_button;
//    private Button number_7_button;
//    private Button number_8_button;
//    private Button number_9_button;
//
//    private Button clear_button;
//    private Button undo_button;
//
//    private Button dot_button;
//
//    private Button plus_button;
//    private Button minus_button;
//    private Button multiply_button;
//    private Button divide_button;
//    private Button exp_button;
//    private Button radic_button;
//    private Button equals_button;
//
//    private Button open_bracket_button;
//    private Button close_bracket_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize_widgets();
    }

    private void initialize_widgets() {
        input_output_field = (EditText) findViewById(R.id.input_output_edit_text);
    }

    public void on_calculator_button(View view) {
        Button pressed_button = (Button) view;
        String button_text = pressed_button.getText().toString();

        if (button_text.equals("=")) {
            calculate();
            return;
        }

        if (button_text.equals(".")) {
            add_dot();
            return;
        }

        if (button_text.equals("<")) {
            undo();
            return;
        }

        if (!text_history.isEmpty()) {
            String current_text = text_history.get(text_history.size()-1);
            String new_text = current_text + button_text;
            set_text(new_text);
        } else {
            set_text(button_text);
        }

        while (text_history.size() > 100) {
            text_history.remove(0);
        }
    }

    private void calculate() {

    }

    private void undo() {
        if (text_history.isEmpty()) {
            return;
        } else if (text_history.size() == 1) {
            input_output_field.setText("0");
            text_history.remove(0);
        } else {
            String previous_text = text_history.get(text_history.size()-2);
            input_output_field.setText(previous_text);
            text_history.remove(text_history.size()-1);
        }
    }

    private void add_dot() {
        String current_text;
        if(!text_history.isEmpty()) {
            current_text = text_history.get(text_history.size()-1) + ".";
        } else {
            current_text = "0.";
        }
        set_text(current_text);
    }

    private void set_text(String text) {
        text_history.add(text);
        input_output_field.setText(text);
    }
}
