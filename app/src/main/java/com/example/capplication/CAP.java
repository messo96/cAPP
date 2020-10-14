package com.example.capplication;

public enum CAP {

        CINESE("IL CAP CINESE",R.drawable.capcinese),
        TAPPE("CI SEI ALLE TAPPE? PATACCA",R.drawable.capciseialletappe),
        FAKETAXI("FAKE TAXI",R.drawable.capfaketaxi),
        BRACCIO("IL BICIPITE DEL CAP",R.drawable.capbracciogonfio),
        BORGHETTI("BORGHETTI TI METTE LE ALI", R.drawable.capborghetti),
        CUSTODE("AFFI TOGLITI LE SCARPE QUANDO ENTRI", R.drawable.capcustode),
        URLO("CHIAMATEMI CRISTIANO",R.drawable.capurlo);

        private String description;
        private int source;

        private CAP(final String description, final Integer source)
        {
            this.description = description;
            this.source = source;

        }

        public String getDescription() {
            return description;
        }

        public Integer getSource() {
            return source;
        }

}
