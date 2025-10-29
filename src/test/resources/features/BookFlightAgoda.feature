Feature: Book Flight functionality

Scenario Outline: User can book flight successfully
    When User can access web Agoda
    Then User click pesawat button
    When User click PulangPergi button
    Then User input dan pilih bandara keberangkatan "<bandaraberangkat>" dan destinasi "<bandaradestinasi>"
    When User click tgl brgkt dan tgl pulang
    Then User click tombol penumpang dan cari penerbangan
    When User filter maskapai "AirAsia"
    Then User pilih maskapai terpilih
    When User verifikasi detail tiket
    Then User isi informasi kontak
    Examples:
        |bandaraberangkat   | bandaradestinasi  |
        |Jakart             | Singapur          |
