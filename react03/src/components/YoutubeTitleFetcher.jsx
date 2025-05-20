import React, {useState} from 'react'
import axios from 'axios'

export default function YoutubeTitleFetcher() {

    const [videoId, setVideoId] = useState("");
    const [title, setTitle] = useState("");
    const [artist, setArtist] = useState("");
    const [url, setUrl] = useState("");
    const [error, setError] = useState(null);

    const API_KEY = "AIzaSyD0v14ARuURnCw0kbPR4g1kaZXjN0KEGEQ"; // need to be fixed in .evn file

    const extractVideoId = (youtubeUrl) => {
        const regex = /(?:https?:\/\/)?(?:www\.)?youtube\.com\/watch\?v=([^&]+)/;
        const match = youtubeUrl.match(regex);
        return match ? match[1] : null;
      };

    const fetchVideo = async () => {
        const extractedVideoId = extractVideoId(url);
    if (!extractedVideoId) {
      setError('잘못된 YouTube URL입니다.');
      setVideoId('');
      setTitle('');
      setArtist('');
      return;
    }
    setVideoId(extractedVideoId);
        try{
            const response = await axios.get(`https://www.googleapis.com/youtube/v3/videos`, {
                params : {
                    part : 'snippet',
                    id: extractedVideoId,
                    key: API_KEY
                }
            });
            setTitle(response.data.items[0].snippet.title);
            setArtist(response.data.items[0].snippet.channelTitle);

            
            // const request = await axios.post(`http://localhost:8080/api/v1/player/add`, data,
            //  {headers: {'Content-Type': 'application/json',},
            // })
        }catch(err) {
            setError("Failed Fatching : " + err);
            setArtist("");
            setTitle("");
        }

        const data = {
            videoId: videoId,
            title: title,
            artist: artist,
            url: url
        }
        console.log(data, videoId)
    }

  return (
    <div>
        <input 
         type="text"
         value={url}
         onChange={(e) => setUrl(e.target.value)}
         placeholder="URL"
        />
        <button onClick={fetchVideo}>Fetch</button>
        {title && <h2>Title: {title}</h2>}
      {artist && <h2>Artist: {artist}</h2>}
      {videoId && <h2>VideoId: {videoId}</h2>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  )
}
