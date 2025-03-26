module.exports = function override(config) {
    config.resolve.fallback = {
      ...config.resolve.fallback,
      "buffer": require.resolve("buffer/"),
      "path": require.resolve("path-browserify"),
    };
    return config;
  };
  