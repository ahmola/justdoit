import React from 'react'
import "../style/Controls.css"

export default function controls({ isShuffle, setIsShuffle, repeatOne, setRepeatOne }) {
  return (
    <div className="controls">
    <button onClick={() => setIsShuffle(!isShuffle)}>
      {isShuffle ? '셔플 끄기' : '셔플 켜기'}
    </button>
    <button onClick={() => setRepeatOne(!repeatOne)}>
      {repeatOne ? '반복 끄기' : '반복 켜기'}
    </button>
  </div>
  )
}
