import React from 'react'
import YouTube from 'react-youtube';
import '../style/VideoPlayer.css';

export default function VideoPlayer({ videoId, onVideoEnd }) {
    const onReady = (event) => {
        event.target.playVideo();
      };
    
      const onError = (event) => {
        console.log('비디오 재생 오류:', event.data);
        onVideoEnd();
      };

    const videoOpts = {
        height: '390',
        width: '640',
        playerVars: {
          autoplay: 1,
          controls: 1,
        },
      };
    
      return (
        <div className="video-player">
          <YouTube 
          videoId={videoId} 
          onReady={onReady}
          onEnd={onVideoEnd}
          onError={onError}
          opts={videoOpts} />
        </div>
      )
}
