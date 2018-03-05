const path = require("path")
const HtmlWebpackPlugin = require("html-webpack-plugin")
const CleanWebpackPlugin = require("clean-webpack-plugin")
const webpack = require("webpack")

const builtPath = "src/main/resources/static/built"

const config = {
  mode: "development",
  entry: ["./src/ui/index.js"],
  devtool: "inline-source-map",
  devServer: {
    contentBase: builtPath,
    port: 3000,
    hot: true,
    proxy: { "**": "http://localhost:8999" }
  },
  output: {
    path: path.resolve(__dirname, builtPath),
    filename: "bundle.js"
  },
  plugins: [
    new CleanWebpackPlugin(["build"]),
    new HtmlWebpackPlugin({
      filename: "../../templates/index.html",
      template: "index.html"
    }),
    new webpack.NamedModulesPlugin(),
    new webpack.HotModuleReplacementPlugin()
  ],
  optimization: {
    minimize: false
  },
  module: {
    rules: [
      { test: /\.js$/, exclude: /node_modules/, use: "babel-loader" },
      { test: /\.css$/, use: ["style-loader", "css-loader"] },
      { test: /\.scss$/, use: ["style-loader", "css-loader", "sass-loader"] },
      { test: /\.(png|svg|img|gif)$/, use: "file-loader" },
      { test: /\.(woff|woff2|eot|ttf|otf)$/, use: "file-loader" }
    ]
  }
}

module.exports = config
