const path =  require('path');

exports.signin = (req, res, next) => {
  res.status(200).sendFile(path.join(__dirname,'../public/views','singin.html'));
};

