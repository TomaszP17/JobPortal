import { useState } from 'react'
import { Menu, X } from 'lucide-react'

export default function Navbar() {
    const [isOpen, setIsOpen] = useState(false)

    const toggleMenu = () => setIsOpen(!isOpen)

    return (
        <nav className="bg-white shadow-sm">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between h-16">
                    <div className="flex items-center">
                        <a href="/" className="text-xl font-bold text-gray-800">
                            Logo
                        </a>
                    </div>
                    <div className="hidden sm:flex sm:items-center">
                        <a href="/" className="px-3 py-2 text-sm font-medium text-gray-700 hover:text-gray-900">
                            Home
                        </a>
                        <a href="/about" className="px-3 py-2 text-sm font-medium text-gray-700 hover:text-gray-900">
                            About
                        </a>
                        <a href="/services" className="px-3 py-2 text-sm font-medium text-gray-700 hover:text-gray-900">
                            Services
                        </a>
                        <a href="/contact" className="px-3 py-2 text-sm font-medium text-gray-700 hover:text-gray-900">
                            Contact
                        </a>
                    </div>
                    <div className="sm:hidden flex items-center">
                        <button onClick={toggleMenu} className="text-gray-500 hover:text-gray-600">
                            {isOpen ? <X size={24} /> : <Menu size={24} />}
                        </button>
                    </div>
                </div>
            </div>
            {isOpen && (
                <div className="sm:hidden">
                    <div className="px-2 pt-2 pb-3 space-y-1">
                        <a href="/" className="block px-3 py-2 text-base font-medium text-gray-700 hover:text-gray-900 hover:bg-gray-50">
                            Home
                        </a>
                        <a href="/about" className="block px-3 py-2 text-base font-medium text-gray-700 hover:text-gray-900 hover:bg-gray-50">
                            About
                        </a>
                        <a href="/services" className="block px-3 py-2 text-base font-medium text-gray-700 hover:text-gray-900 hover:bg-gray-50">
                            Services
                        </a>
                        <a href="/contact" className="block px-3 py-2 text-base font-medium text-gray-700 hover:text-gray-900 hover:bg-gray-50">
                            Contact
                        </a>
                    </div>
                </div>
            )}
        </nav>
    )
}