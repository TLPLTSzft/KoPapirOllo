package com.example.kopapirollo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
	
	private ImageView imageViewGepElet1, imageViewGepElet2, imageViewGepElet3, imageViewJatekosElet1, imageViewJatekosElet2, imageViewJatekosElet3, imageViewGepValasztasa, imageViewJatekosValasztasa, imageViewValasztasKo, imageViewValasztasPapir, imageViewValasztasOllo;
	private TextView textViewDontetlenekSzama;
	private int dontetlenekSzama, gepValasztasa, jatekosValasztasa, gepElet, jatekosElet;
	private AlertDialog.Builder builderJatekVege;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		ujJatek();
		imageViewValasztasKo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				jatekosValasztasa = 1;
				imageViewJatekosValasztasa.setImageResource(R.drawable.rock);
				valasztasok(jatekosValasztasa);
			}
		});
		imageViewValasztasPapir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				jatekosValasztasa = 2;
				imageViewJatekosValasztasa.setImageResource(R.drawable.paper);
				valasztasok(jatekosValasztasa);
			}
		});
		imageViewValasztasOllo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				jatekosValasztasa = 3;
				imageViewJatekosValasztasa.setImageResource(R.drawable.scissors);
				valasztasok(jatekosValasztasa);
			}
		});
	}
	
	private void valasztasok(int jatekosValasztasa) {
		Random random = new Random();
		gepValasztasa = random.nextInt(3) + 1;
		if (gepValasztasa == 1) {
			imageViewGepValasztasa.setImageResource(R.drawable.rock);
		} else if (gepValasztasa == 2) {
			imageViewGepValasztasa.setImageResource(R.drawable.paper);
		} else {
			imageViewGepValasztasa.setImageResource(R.drawable.scissors);
		}
		if ((gepValasztasa ==1 && jatekosValasztasa==2)||(gepValasztasa ==2 && jatekosValasztasa==3)||(gepValasztasa ==3 && jatekosValasztasa==1)) {
			gepEletLevon();
			Toast.makeText(MainActivity.this, "Játékos nyert", Toast.LENGTH_SHORT).show();
		} else if ((gepValasztasa ==1 && jatekosValasztasa==3)||(gepValasztasa ==2 && jatekosValasztasa==1)||(gepValasztasa ==3 && jatekosValasztasa==2)) {
			jatekosEletLevon();
			Toast.makeText(MainActivity.this, "Gép nyert", Toast.LENGTH_SHORT).show();
		} else if (gepValasztasa == jatekosValasztasa) {
			dontetlenekSzama++;
			textViewDontetlenekSzama.setText("Döntetlenek száma: " + String.valueOf(dontetlenekSzama));
			Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void init() {
		imageViewGepElet1 = findViewById(R.id.imageViewGepElet1);
		imageViewGepElet2 = findViewById(R.id.imageViewGepElet2);
		imageViewGepElet3 = findViewById(R.id.imageViewGepElet3);
		imageViewJatekosElet1 = findViewById(R.id.imageViewJatekosElet1);
		imageViewJatekosElet2 = findViewById(R.id.imageViewJatekosElet2);
		imageViewJatekosElet3 = findViewById(R.id.imageViewJatekosElet3);
		textViewDontetlenekSzama = findViewById(R.id.textViewDontetlenekSzama);
		imageViewGepValasztasa = findViewById(R.id.imageViewGepValasztasa);
		imageViewJatekosValasztasa = findViewById(R.id.imageViewJatekosValasztasa);
		imageViewValasztasKo = findViewById(R.id.imageViewValasztasKo);
		imageViewValasztasPapir = findViewById(R.id.imageViewValasztasPapir);
		imageViewValasztasOllo = findViewById(R.id.imageViewValasztasOllo);
		builderJatekVege = new AlertDialog.Builder(MainActivity.this);
		builderJatekVege
			.setCancelable(false)
			.setTitle("A gép nyert / A játékos nyert")
			.setMessage("Szeretne új játékot játszani?")
			.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					finish();
				}
			})
			.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					ujJatek();
				}
			})
			.create();
	}
	
	private void gepEletLevon() {
		switch (gepElet) {
			case 3:
				imageViewGepElet1.setImageResource(R.drawable.heart1);
				break;
			case 2:
				imageViewGepElet2.setImageResource(R.drawable.heart1);
				break;
			case 1:
				imageViewGepElet3.setImageResource(R.drawable.heart1);
				break;
		}
		gepElet--;
		if (gepElet < 1) {
			builderJatekVege.setTitle("A játékos nyert").create().show();
		}
	}
	
	private void jatekosEletLevon() {
		switch (jatekosElet) {
			case 3:
				imageViewJatekosElet3.setImageResource(R.drawable.heart1);
				break;
			case 2:
				imageViewJatekosElet2.setImageResource(R.drawable.heart1);
				break;
			case 1:
				imageViewJatekosElet1.setImageResource(R.drawable.heart1);
				break;
		}
		jatekosElet--;
		if (jatekosElet < 1) {
			builderJatekVege.setTitle("A gép nyert").create().show();
		}
	}
	
	private void ujJatek() {
		gepElet = 3;
		jatekosElet = 3;
		dontetlenekSzama = 0;
		textViewDontetlenekSzama.setText("Döntetlenek száma: 0");
		imageViewGepElet1.setImageResource(R.drawable.heart2);
		imageViewGepElet2.setImageResource(R.drawable.heart2);
		imageViewGepElet3.setImageResource(R.drawable.heart2);
		imageViewJatekosElet1.setImageResource(R.drawable.heart2);
		imageViewJatekosElet2.setImageResource(R.drawable.heart2);
		imageViewJatekosElet3.setImageResource(R.drawable.heart2);
		imageViewGepValasztasa.setImageResource(R.drawable.rock);
		imageViewJatekosValasztasa.setImageResource(R.drawable.rock);
	}
}
