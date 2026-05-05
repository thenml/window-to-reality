# window-to-reality

Simple no dependency mod to modify the Minecraft window to allow transparency. Made for video creators and map makers.

## Features

- Makes the Minecraft game window support transparency.
- Adds four utility blocks with different breakable/passthrough combinations:

  - `cutoutBlock` - breakable, solid
  - `cutoutBarrier` - unbreakable, solid
  - `cutoutWindow` - breakable, passthrough
  - `cutoutPortal` - unbreakable, passthrough

## Transparent Sky

Optional JVM flag:

```bash
-DWTR_CLEAR_SKY=true
```

Enables transparent sky rendering by modifying the sky shader and removing fog.

## Version Support

- A base implementation exists for all supported Minecraft versions.
- Active updates are primarily done for the latest version.
- Older versions may receive updates if requested or contributed through community pull requests.

## Planned

- Possible replacement of the JVM flag with a resource pack approach
- Bug fixes and maintenance improvements

No gameplay or content features are currently planned.

## Compatibility

Clear sky JVM flag can break with modified shader code.

The main mixins are `net.nml.windowtoreality.client.mixin.WindowMixin` and `net.nml.windowtoreality.client.mixin.VulkanGpuSurfaceMixin` (for 26.2+)