const express = require("express");
const router = express.Router();

const UserController = require('../controller/user-controller');

router.get("/signin",  UserController.signin);
module.exports = router;