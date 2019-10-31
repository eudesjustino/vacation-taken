const express = require("express");
const router = express.Router();

const UserController = require('../controller/user-controller');
//const checkAuth = require('../middleware/check-auth');

router.post("/signin",  UserController.signin);
module.exports = router;