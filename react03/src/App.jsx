// src/App.js
import React, { useState } from 'react';
import axios from 'axios';
import Playlist from './components/Playlist';
import VideoPlayer from './components/VideoPlayer';
import VideoInput from './components/VideoInput';
import './App.css';

const App = () => {
  const [videoId, setVideoId] = useState('');
  const [url, setUrl] = useState('');
  const [title, setTitle] = useState('');
  const [playlist, setPlaylist] = useState([]);

  const extractVideoId = (url) => url.match(/(?:youtube\.com\/watch\?v=|youtu\.be\/)([a-zA-Z0-9_-]{11})/)?.[1];

  const handleSubmit = (e) => {
    e.preventDefault();
    const id = extractVideoId(url);
    if (id && title && !playlist.some(video => video.id === id)) {
      async () => {
        const response = await axios.post("http://localhost:8080/api/v1/player/add", {
          title,
          artist,
          url
        });

        if(response.status == 200){
            
        }
      }

      setPlaylist([...playlist, { id, title }]);
      setUrl('');
      setTitle('');
    } else {
      alert('유효한 YouTube URL과 제목을 입력하세요. 중복된 동영상은 추가할 수 없습니다.');
    }
  };

  const playVideo = (id) => {
    setVideoId(id);
  };

  return (
    <div className="app-container">
      <div className="form-container">
        <h1>YouTube 플레이어</h1>
        <VideoInput url={url} setUrl={setUrl} title={title} setTitle={setTitle} handleSubmit={handleSubmit} />
        {videoId && <VideoPlayer videoId={videoId} />}
      </div>
      <Playlist playlist={playlist} playVideo={playVideo} currentVideoId={videoId} setPlaylist={setPlaylist} />
    </div>
  );
};

export default App;