use('songsDB');

db.getCollection('songs').insertMany([
  { title: 'insert title here', artist: 'insert artist here', genre: 'insert genre here', mood: 'insert mood here', dateReleased: 'yyyy-dd-mm', imageBase64: '' },
  // ... rest of your songs
]);

console.log('Songs inserted!');