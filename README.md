# window-to-reality

Simple mod to modify the Minecraft window to allow transparency. Made for video creators and map makers for when a chromakey is not enough.

Fabric API is optional.

## Features

- Makes the Minecraft game window support transparency.
- Adds four utility blocks with different breakable/passthrough combinations:
  - `cutout` - breakable, solid
  - `cutout_barrier` - unbreakable, solid
  - `cutout_window` - breakable, passthrough
  - `cutout_void` - unbreakable, passthrough

## Version Support

- Active updates are primarily done for the latest version and latest x.1.y
- Older versions may receive releases and updates if requested or contributed through community pull requests.

No additional content features are currently planned. NeoForge support not planned.

## Compatibility

- Tested and works on: Windows, Linux Wayland (XWayland doesn't work - use `-DMC_DEBUG_ENABLED -DMC_DEBUG_PREFER_WAYLAND` JVM args)
- No guarantee that mod custom screenshotting or rendering will work.
- Transparency supported with Improved Transparency setting on
