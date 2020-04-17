function startRecord(){
    navigator.getDisplayMedia({ video: true })
  .then(stream => {
    // we have a stream, we can record it
    const chunks = [];
    const recorder = new MediaRecorder(stream);
    recorder.ondataavailable = e => chunks.push(e.data);
    recorder.onstop = e => exportVid(new Blob(chunks));
    recorder.start();
    // defineWhenWeStopRecorder(recorder)
  }, error => {
    console.log("Unable to acquire screen capture", error);
  });
}