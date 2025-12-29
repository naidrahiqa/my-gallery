from PIL import Image
import os

# Buat folder mipmap jika belum ada
densities = {
    'mipmap-mdpi': 48,
    'mipmap-hdpi': 72,
    'mipmap-xhdpi': 96,
    'mipmap-xxhdpi': 144,
    'mipmap-xxxhdpi': 192
}

# Base path
base_path = 'app/src/main/res'

# Buka icon asli
icon = Image.open('icon.jpg')

# Convert ke RGBA untuk PNG
if icon.mode != 'RGBA':
    icon = icon.convert('RGBA')

# Generate untuk setiap density
for folder, size in densities.items():
    # Buat folder jika belum ada
    folder_path = os.path.join(base_path, folder)
    os.makedirs(folder_path, exist_ok=True)
    
    # Resize icon
    resized = icon.resize((size, size), Image.Resampling.LANCZOS)
    
    # Simpan sebagai PNG
    output_path = os.path.join(folder_path, 'ic_launcher.png')
    resized.save(output_path, 'PNG')
    print(f'✓ Created {output_path} ({size}x{size})')
    
    # Juga buat ic_launcher_round.png (sama)
    output_path_round = os.path.join(folder_path, 'ic_launcher_round.png')
    resized.save(output_path_round, 'PNG')
    print(f'✓ Created {output_path_round} ({size}x{size})')

print('\n✅ All launcher icons generated successfully!')
