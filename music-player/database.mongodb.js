use('songsDB');

db.getCollection('songs').insertMany([
  // Pop
  {
    title: 'Billie Jean',
    artist: 'Michael Jackson',
    genre: 'Pop',
    mood: 'Energetic',
    dateReleased: '1983-01-02',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273230da28b7b53e7a2192e1743'
  },

  {
    title: 'Bohemian Rhapsody',
    artist: 'Queen',
    genre: 'Pop',
    mood: 'Energetic',
    dateReleased: '1975-10-31',
    imageUrl: 'https://i.scdn.co/image/ab67616d00001e0231988dbd4a501b2f376f8e34'
  },

  {
    title: 'Dancing Queen',
    artist: 'ABBA',
    genre: 'Pop',
    mood: 'Happy',
    dateReleased: '1976-08-27',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b27370f7a1b35d5165c85b95a0e0'
  },

  {
    title: 'I Will Always Love You',
    artist: 'Whitney Houston',
    genre: 'Pop',
    mood: 'Sad',
    dateReleased: '1992-11-03',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273a500339114058474d1ee6f7e'
  },

  {
    title: 'Shape of You',
    artist: 'Ed Sheeran',
    genre: 'Pop',
    mood: 'Happy',
    dateReleased: '2017-01-06',
    imageUrl: 'https://upload.wikimedia.org/wikipedia/en/b/b8/Ed_Sheeran_-_Shape_of_You_%28Official_Single_Cover%29.png'
  },

  // Country
  {
    title: 'Chicken Fried',
    artist: 'Zac Brown Band',
    genre: 'Country',
    mood: 'Happy',
    dateReleased: '2008-07-08',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273ba5db46f4b838ef6027e6f96'

  },

  {
    title: 'Jolene',
    artist: 'Dolly Parton',
    genre: 'Country',
    mood: 'Sad',
    dateReleased: '1973-10-15',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b27359532597b51b2f64811bc9b3'

  },

  {
    title: 'Take Me Home, Country Roads',
    artist: 'John Denver',
    genre: 'Country',
    mood: 'Calm',
    dateReleased: '1971-04-12',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273a19cee952b6dae128d68f116'

  },

  {
    title: 'Before He Cheats',
    artist: 'Carrie Underwood',
    genre: 'Country',
    mood: 'Energetic',
    dateReleased: '2005-10-26',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b2734bc55b98f352c91c78f2aa21'
  },

  {
    title: 'Bless the Broken Road',
    artist: 'Rascal Flatts',
    genre: 'Country',
    mood: 'Calm',
    dateReleased: '2004-08-17',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273d1ca2ddde8ff17e3991de83d'
  },

  // Rock
  {
    title: 'Back in Black',
    artist: 'AC/DC',
    genre: 'Rock',
    mood: 'Energetic',
    dateReleased: '1980-07-25',
    imageUrl: 'https://i.scdn.co/image/ab67616d00001e020925ea2f93242511f6482f38'
  },

  {
    title: 'Welcome to the Jungle',
    artist: 'Guns N Roses',
    genre: 'Rock',
    mood: 'Energetic',
    dateReleased: '1987-09-28',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b27321ebf49b3292c3f0f575f0f5'

  },

  {
    title: 'Smells Like Team Spirit',
    artist: 'Nirvana',
    genre: 'Rock',
    mood: 'Energetic',
    dateReleased: '1991-09-10',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273e175a19e530c898d167d39bf'

  },

  {
    title: 'Stairway to Heaven',
    artist: 'Led Zeppelin',
    genre: 'Rock',
    mood: 'Calm',
    dateReleased: '1971-11-08',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273e1773ff68e5a63841a1ade09'

  },

  {
    title: 'Tom Sawyer',
    artist: 'Rush',
    genre: 'Rock',
    mood: 'Energetic',
    dateReleased: '1981-02-09',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b27372833c1ae3343cbfb4617073'

  },

  // Rap
  {
    title: 'Not Like Us',
    artist: 'Kendrick Lamar',
    genre: 'Rap',
    mood: 'Energetic',
    dateReleased: '2024-05-04',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b2731ea0c62b2339cbf493a999ad'
  },

  {
    title: 'Lose Yourself',
    artist: 'Eminem',
    genre: 'Rap',
    mood: 'Energetic',
    dateReleased: '2002-10-28',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273968da85f248a7e7747767801'

  },

  {
    title: 'Stan',
    artist: 'Eminem',
    genre: 'Rap',
    mood: 'Sad',
    dateReleased: '2000-11-13',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273660b251d25d51665990552ec'

  },

  {
    title: 'C.R.E.A.M',
    artist: 'Wu-Tang Clan',
    genre: 'Rap',
    mood: 'Calm',
    dateReleased: '1993-03-09',
    imageUrl: 'https://i.scdn.co/image/ab67616d00001e02f7d3ba2473d4452b83ba61c4'
  },

  {
    title: 'In Da Club',
    artist: '50 Cent',
    genre: 'Rap',
    mood: 'Happy',
    dateReleased: '2003-01-07',
    imageUrl: 'https://i.scdn.co/image/ab67616d0000b273d843fabb75fef14010e30cae'
  },


]);

console.log('Songs inserted!');