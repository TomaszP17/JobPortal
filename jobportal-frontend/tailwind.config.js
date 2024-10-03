/** @type {import('tailwindcss').Config} */
export default {
    darkMode: ["class"],
	content: [
		'./index.html',
		'./src/**/*.{js,jsx,ts,tsx}',
	],
  theme: {
  	extend: {
  		borderRadius: {
  			lg: 'var(--radius)',
  			md: 'calc(var(--radius) - 2px)',
  			sm: 'calc(var(--radius) - 4px)'
  		},
  		colors: {
  			background: 'hsl(var(--background))',
  			foreground: 'hsl(var(--foreground))',
  			card: {
  				DEFAULT: 'hsl(var(--card))',
  				foreground: 'hsl(var(--card-foreground))'
  			},
  			popover: {
  				DEFAULT: 'hsl(var(--popover))',
  				foreground: 'hsl(var(--popover-foreground))'
  			},
  			primary: {
  				DEFAULT: 'hsl(var(--primary))',
  				foreground: 'hsl(var(--primary-foreground))'
  			},
  			secondary: {
  				DEFAULT: 'hsl(var(--secondary))',
  				foreground: 'hsl(var(--secondary-foreground))'
  			},
  			muted: {
  				DEFAULT: 'hsl(var(--muted))',
  				foreground: 'hsl(var(--muted-foreground))'
  			},
  			accent: {
  				DEFAULT: 'hsl(var(--accent))',
  				foreground: 'hsl(var(--accent-foreground))'
  			},
  			destructive: {
  				DEFAULT: 'hsl(var(--destructive))',
  				foreground: 'hsl(var(--destructive-foreground))'
  			},
  			border: 'hsl(var(--border))',
  			input: 'hsl(var(--input))',
  			ring: 'hsl(var(--ring))',
  			chart: {
  				'1': 'hsl(var(--chart-1))',
  				'2': 'hsl(var(--chart-2))',
  				'3': 'hsl(var(--chart-3))',
  				'4': 'hsl(var(--chart-4))',
  				'5': 'hsl(var(--chart-5))'
  			},
			electricPurple: {
				100: '#f1e6ff',
				200: '#dab3ff',
				300: '#c380ff',
				400: '#ac4dff',
				500: '#8a2be2',
				600: '#6d1cc1',
				700: '#4f139f',
				800: '#320a7e',
				900: '#1b025d',
			},
			matteBlack: {
				100: '#e6e6e6',
				200: '#cccccc',
				300: '#b3b3b3',
				400: '#808080',
				500: '#4d4d4d',
				600: '#333333',
				700: '#1a1a1a',
				800: '#0f0f0f',
				900: '#050505',
			}
  		}
  	}
  },
  plugins: [require("tailwindcss-animate")],
}

