module.exports = {
  //enable es7 static method
  "parser": "babel-eslint",
  "extends": [ "eslint:recommended", "plugin:react/recommended" ],
  "env": {
    "node": true,
    "browser": true,
  },
  "parserOptions": {
    "ecmaVersion": 6,
    "ecmaFeatures": {
        jsx: true,
    },
    "sourceType": "module"
  }
};