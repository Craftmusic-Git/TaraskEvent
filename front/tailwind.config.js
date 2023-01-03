/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: [
    "./node_modules/flowbite-react/**/*.js",
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./src/shared/**/*.{js,ts,jsx,tsx}",
    "./public/**/*/html",
  ],
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        'smoky-black': '#0C0A09',
        'space-cadet': '#16123E',
        'caroline': '#009FFD',
        'cream': '#F3F7F0',
        'paradise': '#EC255A',
      }
    },
  },
  plugins: [
    require("flowbite/plugin")
  ],
}
