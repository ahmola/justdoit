import React, {useState, useEffect} from 'react'
import '../style/VideoInput.css'

export default function VideoInput({ url, setUrl, title, setTitle, handleSubmit }) {

  return (
    <form onSubmit={handleSubmit} className="video-input-form">
    <input
      type="text"
      placeholder="YouTube URL을 입력하세요"
      value={url}
      onChange={(e) => setUrl(e.target.value)}
      className="input-url"
    />
    <input
      type="text"
      placeholder="비디오 제목을 입력하세요"
      value={title}
      onChange={(e) => setTitle(e.target.value)}
      className="input-title"
    />
    <button type="submit">재생목록에 추가</button>
  </form>
  )
}