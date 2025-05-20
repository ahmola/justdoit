import React from 'react'
import '../style/Playlist.css'

export default function Playlist({ playlist, playVideo, currentVideoId, setPlaylist  }) {
    const currentIndex = playlist.findIndex(video => video.id === currentVideoId);

    const moveToPrevious = () => {
      if (currentIndex > 0) {
        playVideo(playlist[currentIndex - 1].id); // 이전 비디오 재생
      } else{
          playVideo(playlist[playlist.length - 1].id)
      }
    };
  
    const moveToNext = () => {
      if (currentIndex < playlist.length - 1) {
        playVideo(playlist[currentIndex + 1].id); // 다음 비디오 재생
      }else{
          playVideo(playlist[0].id)
      }
    };

    const removeVideo = (id) => {
        const newPlaylist = playlist.filter(video => video.id !== id);
        setPlaylist(newPlaylist);
        if (newPlaylist.length === 0) {
            setPlaylist([]); // 재생목록을 비움
            playVideo(''); // 비디오 중지
        } else {
            if (currentVideoId === id) {
                const nextIndex = currentIndex === newPlaylist.length ? currentIndex - 1 : currentIndex + 1;
                playVideo(newPlaylist[nextIndex]?.id);
            }
        }
      };

      const removePlaylist = () => {
          setPlaylist([]);
      }

    return (
        <div className="playlist-container">
        <h2>
          재생 목록
            <span className="track-controls">
              <button onClick={moveToPrevious}>↑</button>
              <button onClick={moveToNext}>↓</button>
              <button onClick={removePlaylist}>⚡</button>
            </span>
        </h2>
        <ul>
          {playlist.map((video) => (
            <li key={video.id} className="playlist-item" onClick={() => playVideo(video.id)}>
            <span>{video.title}</span>
            <button className="remove-button" onClick={() => removeVideo(video.id)}>✖</button>
          </li>
          ))}
        </ul>
      </div>
      )
}
